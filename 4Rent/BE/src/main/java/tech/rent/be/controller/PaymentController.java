//package tech.rent.be.controller;
//
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tech.rent.be.dto.BookingRequestDTO;
//import tech.rent.be.dto.PaymentDTO;
//import tech.rent.be.dto.RealEstateDTO;
//import tech.rent.be.entity.Booking;
//import tech.rent.be.entity.Payment;
//import tech.rent.be.entity.RealEstate;
//import tech.rent.be.services.BookingService;
//import tech.rent.be.services.PaymentService;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//import java.util.TreeMap;
//
//@RestController
//@CrossOrigin
//@SecurityRequirement(name = "api")
//public class PaymentController {
//    @Autowired
//    PaymentService paymentService;
//
//    @Autowired
//    BookingService bookingService;
////    @PostMapping("/Payment")
////    public ResponseEntity payment(@RequestBody PaymentDTO paymentDTO){
////        Payment payment = paymentService.createPayment(paymentDTO);
////        return  ResponseEntity.ok(paymentDTO);
////    }
//
//
//    @PostMapping("/vn-pay")
//    public ResponseEntity createOrder(@RequestBody BookingRequestDTO bookingRequestDTO) throws Exception {
//        String url = bookingService.getVnPay(bookingRequestDTO);
//        return  ResponseEntity.ok(url);
//    }
//
//    @GetMapping("/success")
//    public ResponseEntity<Booking> update(@RequestParam long vnp_TxnRef){
//        Booking booking = bookingService.updatePayment(vnp_TxnRef);
//        return ResponseEntity.ok(booking);
//    }
//}
