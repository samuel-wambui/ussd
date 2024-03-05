package com.equifarm.USSD;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

    @RestController("/ussd")
    public class Controller {
        private String prompt;
        private int currentState = 0;
        private Queue<String> prompts = new LinkedList<>(); //Queue responses prior to dispatch
        private List<String> userInput = new ArrayList<>(); // Array to store user input

        /*private String sessionId;
        private String phoneNumber;
        private String networkCode;
        private String serviceCode;*/
        private String response;

        @PostMapping("/ussd")
        public String ussdCallback(@RequestParam("sessionId") String sessionId,
                                   @RequestParam("phoneNumber") String phoneNumber,
                                   @RequestParam("networkCode") String networkCode,
                                   @RequestParam("serviceCode") String serviceCode,
                                   @RequestParam("text") String response) {
        /*this.sessionId = sessionId;
        this.phoneNumber = phoneNumber;
        this.networkCode = networkCode;
        this.serviceCode = serviceCode;*/
            this.response = response;

                prompts.offer("CON Enter First name:");
                prompts.offer("CON Enter Middle name:");
                prompts.offer("CON Enter Last name:");
                prompts.offer("CON Enter Id no:");
                prompts.offer("CON Enter location:");
                prompts.offer("CON Set equifarm pin:");
                prompts.offer("CON confirm pin:");
                prompts.offer("CON 1. Accept and confirm\n 2. Exit");

            if (response.isEmpty()) {
                prompt = main();
            /*prompt = "CON Welcome to Equifarm: \n";
            prompt += "1. Register \n";
            prompt += "2. Exit \n";

           ( prompt += "3. Claim \n";
            prompt += "4. Contact us \n";
            prompt += "5. Exit";)*/

            } else {
                switch (response) {
                    case "1":
                        prompt = registration();
                        break;
                    case "2":
                        prompt = bookPolicy();
                        break;
                    case "3":
                        prompt = claim();
                        break;
                    case "4":
                        prompt = contact();
                        break;

                    case "5":
                        prompt = exit();
                        break;
                    case "1*1":
                        prompt = policyStatus();
                    case "1*2":
                        prompt =  policyStatusDomestic();
                        break;
                    case "1*2*1":
                        prompt = statusDomestic();
                    case "1*1*1":
                        prompt = status();
                        break;
                    case "2*1":
                        while (!prompts.isEmpty()) {
                            sendPrompt(bookThirdParty());
                            currentState++;
                        }
                        break;
                    /*case "2*2":
                        prompt = bookDomestic();
                        break;*/
                    case "1*1*0", "1*2*0":
                        prompt = registration();
                        break;
                    case "1*1*00":
                        prompt = main();
                        break;
                    default:
                        prompt = "Invalid selection";
                        break;
                }
            }
            return prompt;
        }

        public String main() {
            if(!response.isEmpty()) {
                response = "";
            }

            prompt = "CON Welcome to Equifarm: \n";
            prompt += "1. registration \n";
            prompt += "2. Exit \n";
            prompt += "3. Claim \n";
            prompt += "4. Contact us \n";
            prompt += "5. Exit";

            return prompt;
        }

        public String registration() {

           /* prompt = " Enter First name:";
            prompt += " Enter Middle name:";
            prompt += " Enter Last name:";
            prompt += " Enter Id no:";
            prompt += " Enter location:";
            prompt += " Set Equifarm pin:";
            prompt += " Confirm pin:";
            prompt += "CON 1. Accept and confirm\n 2. Exit";
            return prompt;*/
            prompt = "CON first name:";
            String firstname = response;
            prompt = "CON middle name:";
            String middlename = response;
            prompt = "CON last name:";
            String lastname = response;

            prompt = "CON Enter Id no:";
            String idNo = response;

            prompt = "CON Set Location:";
            String location = response;

            prompt = "CON 1. Accept and confirm \n";
            prompt += "2. Exit ";
            return prompt;
        }
        public String bookPolicy() {
            prompt = "CON 1. Third Party \n";
            prompt += "2. Domestic";
            return prompt;
        }
        public String claim() {
            prompt = "CON Claim";
            return prompt;
        }
        public String contact() {
            prompt = "END Contact us";
            return prompt;
        }
        public String exit() {
            prompt = "END Exited";
            return prompt;
        }
        //    Level 2
        public String policyStatus() {
            prompt = "CON 1. Policy status \n";
            prompt += "0. Back \n";
            prompt += "00. Main menu";
            return prompt;
        }
        public String policyStatusDomestic() {
            prompt = "CON 1. Policy status \n";
            prompt += "0. Back \n";
            prompt += "00. Main menu";
            return prompt;
        }
        //    To be continued -> add activate model
        public String status() {
            prompt = "CON Enter plate no:";
            return prompt;
        }
        //    To be continued
        public String statusDomestic() {
            prompt = "CON Enter Id no:";
            return prompt;
        }
        public String bookThirdParty() {
            prompt = prompts.poll();
            userInput.add(currentState, response);
            return prompt;



        /*while (!prompts.isEmpty()) {
            prompt = prompts.poll();
            userInput.add(currentState, response);
            currentState++;
        }

        Iterator<String> iterator = prompts.iterator();

        while (iterator.hasNext()) {
            prompt = iterator.next();
            userInput.add(currentState, response);

            currentState++;

            if (currentState < prompts.size()) {
                userInput.add(currentState, response);
                prompt = prompts.poll();
                currentState++;
            } else {
                switch (response) {
                    case "1":
                        // Process collected data and confirm
                        prompt = "END Thank you for booking. Your request has been received.";
                        break;
                    case "2":
                        // Cancel booking
                        prompt = "END Booking cancelled.";
                        break;
                    default:
                        prompt = "END Invalid input. Please try again.";
                        break;
                }
            }
        }*/


                /*if (currentState < prompts.size()) {
                    userInput.add(currentState, response);
                    prompt = prompts.poll();
                    currentState++;
                    return prompt;
                } else {
                    switch (response) {
                        case "1":
                            // Process collected data and confirm
                            prompt = "END Thank you for booking. Your request has been received.";
                            break;
                        case "2":
                            // Cancel booking
                            prompt = "END Booking cancelled.";
                            break;
                        default:
                            prompt = "END Invalid input. Please try again.";
                            break;
                    }
                }*/







        /*do {
            if (currentState < prompts.length) {
                userInput[currentState] = response;
                prompt = "CON " + prompts[currentState];
                currentState++;
            } else {
                switch (response) {
                    case "1":
                        // Process collected data and confirm
                        prompt = "END Thank you for booking. Your request has been received.";
                        break;
                    case "2":
                        // Cancel booking
                        prompt = "END Booking cancelled.";
                        break;
                    default:
                        prompt = "END Invalid input. Please try again.";
                        break;
                }
            }
        }
        while(currentState < prompts.length);*/


        /*for (String prompt : prompts) {
            prompt = "CON " + prompt;
        }*/

        /*for (currentState = 0; currentState < prompts.length; currentState++) {
            userInput[currentState] = response;
            prompt = "CON" + prompts[currentState];
        }
        if(response.equals("1")) {
            //Process data collected
            prompt = "END Thank you for booking. Your request has been received.";
        }
        else if(response.equals("2")) {
            // Cancel booking
            prompt = "END Booking cancelled.";
        }
        else{
            prompt = "END Invalid input. Please try again.";
        }*/


        /*switch (currentState) {
            case 0:
                break;
            case 1:
                break;
            case 3:
                break;
            case 4:
                userInput[currentState] = response;
                prompt = "CON " + prompts[currentState];
                currentState++;
                break;
            case 5:
                if(response.equals("1")) {
                    //Process data collected
                    prompt = "END Thank you for booking. Your request has been received.";
                }
                else if(response.equals("2")) {
                    // Cancel booking
                    prompt = "END Booking cancelled.";
                }
                else{
                    prompt = "END Invalid input. Please try again.";
                }
                break;
        }*/




        /*prompt = "CON Enter plate no:";
        String plate = response;

        prompt = "CON Enter car model:";
        String model = response;

        prompt = "CON Enter start date:";
        String date = response;

        prompt = "CON Enter Id no:";
        String idNo = response;

        prompt = "CON Enter email:";
        String email = response;

        prompt = "CON 1. Accept and confirm \n";
        prompt += "2. Exit ";*/
        }
       /* public String bookDomestic() {
            prompt = "CON Enter start date:";
            String date = response;

            prompt = "CON Enter Id no:";
            String idNo = response;

            prompt = "CON Enter email:";
            String email = response;

            prompt = "CON 1. Accept and confirm \n";
            prompt += "2. Exit ";

            return prompt;
        }*/
        public  void sendPrompt(String text) {
            prompt = text;
        }
    }



