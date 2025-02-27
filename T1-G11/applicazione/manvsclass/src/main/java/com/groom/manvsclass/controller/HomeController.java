/*
 *   Copyright (c) 2025 Stefano Marano https://github.com/StefanoMarano80017
 *   All rights reserved.

 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/*MODIFICA (5/11/2024) - Refactoring task T1
 * HomeController ora si occupa solo del mapping dei servizi aggiunti.
 */

 package com.groom.manvsclass.controller;

 import java.util.List;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.CookieValue;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.ResponseBody;

 import com.groom.manvsclass.model.ClassUT;
 import com.groom.manvsclass.model.interaction;
 import com.groom.manvsclass.service.AchievementService;
 import com.groom.manvsclass.service.AdminService;
 import com.groom.manvsclass.util.Util;

 
 @CrossOrigin
 @Controller
 public class HomeController {
 
     @Autowired
     private AdminService adminService;

     @Autowired
     private Util utilsService;

     //Solo x testing
     @GetMapping("/getLikes/{name}")
     @ResponseBody
     public ResponseEntity<Long> likes(@PathVariable String name) {
         long likesCount = utilsService.likes(name);
         return ResponseEntity.ok(likesCount);
     }
 
     @PostMapping("/newinteraction") 
     @ResponseBody
     public ResponseEntity<interaction> uploadInteraction(@RequestBody interaction interazione) {
         interaction savedInteraction = utilsService.uploadInteraction(interazione); 
         return ResponseEntity.ok(savedInteraction); 
     }
 
     @GetMapping("/Cfilterby/{category}")
     @ResponseBody
     public ResponseEntity<List<ClassUT>> filtraClassi(@PathVariable String category, @CookieValue(name = "jwt", required = false) String jwt) {
         return adminService.filtraClassi(category, jwt);
     }
 
     @GetMapping("/Cfilterby/{text}/{category}")
     @ResponseBody
     public ResponseEntity<List<ClassUT>> filtraClassi(@PathVariable String text, @PathVariable String category, @CookieValue(name = "jwt", required = false) String jwt) {
         return adminService.filtraClassi(text, category, jwt);
     }

     /*
     @GetMapping("/test")
     @ResponseBody
     public String test() {
         return adminService.test(); 
     }

      */
 
     @GetMapping("/interaction")
     @ResponseBody
     public List<interaction> elencaInt() {
         return utilsService.elencaInt();
     }
 
     @GetMapping("/findReport")
     @ResponseBody
     public List<interaction> elencaReport() {
         return utilsService.elencaReport();
     }
 
 
     @PostMapping("/newLike/{name}")
     @ResponseBody
     public String newLike(@PathVariable String name) {
         return utilsService.newLike(name);
     }
 
     @PostMapping("/newReport/{name}")
     @ResponseBody
     public String newReport(@PathVariable String name, @RequestBody String commento) {
         return utilsService.newReport(name, commento);
     }
 
     @PostMapping("/deleteint/{id_i}")
     @ResponseBody
     public interaction eliminaInteraction(@PathVariable int id_i) {
         return utilsService.eliminaInteraction(id_i);
     }







    
 }



