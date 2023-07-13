package com.cncbinternational.websocketpoc.service;

import com.cncbinternational.websocketpoc.dto.GenerateDto;
import com.cncbinternational.websocketpoc.entity.QRCode;
import com.cncbinternational.websocketpoc.enums.Workflow;
import com.cncbinternational.websocketpoc.repository.TransactionRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    private final SimpMessagingTemplate messagingTemplate;
    private final TransactionRepository transactionRepository;

    public TransactionService(SimpMessagingTemplate messagingTemplate,
                              TransactionRepository transactionRepository) {
        this.messagingTemplate = messagingTemplate;
        this.transactionRepository = transactionRepository;
    }
    public GenerateDto generate(){
        UUID uuid = UUID.randomUUID();
        Date expires = new Date(new Date().getTime() + 60000);
        QRCode qrcode = new QRCode();
        qrcode.setType(Workflow.CREATE);
        qrcode.setExpires(expires);
        qrcode.setId(uuid);
        save(qrcode);
        return new GenerateDto(uuid, expires);
    }

    public Optional<QRCode> findRelation(UUID txId){
        return convertIteratorToList(transactionRepository.findAll()).stream()
                .filter(p -> p.getId().equals(txId))
                .findFirst();

    }
    public Boolean authorize(QRCode profile){
        Optional<QRCode> relation = findRelation(profile.getId());

        if(relation.isPresent()){
            Random rnd = new Random();
            int number = rnd.nextInt(999999);

            QRCode qrcode = relation.get();
            qrcode.setUsername(profile.getUsername());
            qrcode.setType(Workflow.CODE_SENT);
            qrcode.setAuthCode(String.format("%06d", number));
            transactionRepository.save(qrcode);
            sendMessage(qrcode);
            return true;
        }
        return false;
    }

    public void handleSocket(QRCode qrcode) {

        if(qrcode.getType().equals(Workflow.CODE_SENT)) {
            Optional<QRCode> relation = transactionRepository.findById(qrcode.getId());
            if (relation.isPresent() ) {

            } else {
               // sendMessage(generateErrorMessage("QR Code is expired!", loginQRCode.getRoom()));
            }
        }

    }


    public void sendMessage(QRCode qrCode) {
        messagingTemplate.convertAndSend("/topic/loginListener/" + qrCode.getId(), qrCode);
    }
    private List<QRCode> convertIteratorToList(Iterable<QRCode> iterator) {
        List<QRCode> list = new ArrayList<>();
        iterator.iterator().forEachRemaining(list::add);
        return list;
    }

    public void save(QRCode qrcode) {
        Optional<QRCode> optionalObject = convertIteratorToList(transactionRepository.findAll()).stream().
                filter(p -> p.getId().equals(qrcode.getId())).
                findFirst();

        if (optionalObject.isPresent()) {
            var obj = optionalObject.get();
            obj.setId(qrcode.getId());
            transactionRepository.save(obj);
            return;
        }
        transactionRepository.save(qrcode);
    }
}
