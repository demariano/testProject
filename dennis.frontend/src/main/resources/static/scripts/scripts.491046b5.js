"use strict";angular.module("clientApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","ngTouch","ui.bootstrap","mwl.confirm","toaster"]).config(["$routeProvider",function(a){a.when("/",{templateUrl:"views/main.html",controller:"MainCtrl",controllerAs:"main"}).when("/notes",{templateUrl:"views/notes.html",controller:"NotesCtrl",controllerAs:"notes"}).when("/newNotes",{templateUrl:"views/newnotes.html",controller:"NewnotesCtrl",controllerAs:"newNotes"}).when("/viewNotes/:record_id",{templateUrl:"views/viewnotes.html",controller:"ViewnotesCtrl",controllerAs:"viewNotes"}).otherwise({redirectTo:"/"})}]),angular.module("clientApp").controller("MainCtrl",function(){this.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}),angular.module("clientApp").service("notesService",["$http",function(a){this.getRecords=function(){return a.get("http://localhost:8090/notes")},this.getRecord=function(b){return a.get("http://localhost:8090/notes/"+b)},this.createRecord=function(b){return a({method:"POST",url:"http://localhost:8090/notes/",data:b})},this.updateRecord=function(b,c){return console.log("Updating Record :"+b),a({method:"PUT",url:"http://localhost:8090/notes/ "+b,data:c})},this.deleleRecord=function(b){return console.log("Deleting Record :"+b),a({method:"DELETE",url:"http://localhost:8090/notes/"+b})}}]),angular.module("clientApp").controller("NotesCtrl",["$scope","notesService","$location","toaster",function(a,b,c,d){function e(){b.getRecords().then(function(b){a.data=b.data,console.log(a.data)},function(a){JSON.stringify(a);console.log("Unable to Retrieve Data from Backend Server"),d.pop("error","","Unable to Retrieve Data from Backend Server")})}e(),a.createNote=function(){console.log("Create Note Invoked"),c.path("/newNotes")},a.viewNote=function(a){console.log("View Note Invoked"),c.path("/viewNotes/"+a.id)},a.deleteNote=function(a){console.log("Deleting Note Record :"+a.id),b.deleleRecord(a.id).then(function(a){console.log("Record Deleted"),d.pop("success","","Record Deleted"),e()},function(a){JSON.stringify(response);console.log("Unable to Delete Record"),d.pop("error","","Unable to Delete Record")})}}]),angular.module("clientApp").controller("NewnotesCtrl",["$scope","notesService","$location","toaster",function(a,b,c,d){a.record={title:"",content:""},a.saveRecord=function(){console.log("Saving Note Record "+a.record.title+" "+a.record.content),b.createRecord(a.record).then(function(a){c.path("/notes")},function(a){JSON.stringify(a);console.log(a),d.pop("success","","Unable to Create Record")})}}]),angular.module("clientApp").controller("ViewnotesCtrl",["$scope","notesService","$location","$routeParams","toaster",function(a,b,c,d,e){function f(){b.getRecord(d.record_id).then(function(b){a.record=b.data},function(a){JSON.stringify(a);console.log("Unable to Retrieve Data from Backend Server")}),a.editRecord=function(){a.hideSaveButton=!1,a.hideEditButton=!0,a.editEnabled=!1},a.backToHome=function(){c.path("/notes")},a.saveRecord=function(){console.log("Updating Note Record "+a.record.title+" "+a.record.content),b.updateRecord(a.record.id,a.record).then(function(b){e.pop("success","","Record Save"),a.hideSaveButton=!0,a.hideEditButton=!1,a.editEnabled=!0},function(a){JSON.stringify(a);console.log(a),e.pop("error","","Unable to Save Record")})}}a.record={},a.editEnabled=!0,a.hideSaveButton=!0,a.hideEditButton=!1,f()}]),angular.module("clientApp").run(["$templateCache",function(a){a.put("views/main.html",'<div class="row marketing"> <h4>Dennis Mariano Test Project</h4> <li>Backend MicroService <ul> <li>Provides data services for CRUD Operations using Spring Boot</li> <li>Spring Data JPA and H2 In Memory DB</li> <li>SWAGGER URL: http://localhost:8090/swagger-ui.html#/</li> <li>PORT : 8090</li> </ul> </li> <li>Frontend <ul> <li>Provides User Interface for the Users </li> <li>Angular JS , Grunt and BOWER build frameworks</li> <li>DEVELOPMENT PORT : 9000</li> <li>SERVER PORT : 8080</li> <li>CLIENT SOURCE CODE : ../src/client</li> <li>GRUNT BUILD will copy /client/dist folder to : /src/main/resource/static folder</li> </ul> </li> </div>'),a.put("views/newnotes.html",'<toaster-container toaster-options="{\'time-out\': 3000, \'close-button\':true}"></toaster-container> <div class="panel panel-primary"> <div class="panel-heading"> <span class="btn-group pull-right"> <button ng-click="saveRecord()" class="btn btn-success">Save</button> </span> <h4>Notes</h4> </div> <div class="panel-body"> <div class="col-xs-12 col-sm-12"> <form role="form" class="form-horizontal"> <div class="form-group"> <div class="col-xs-3 text-right"> <label for="cpTitle">Title</label> </div> <div class="col-xs-9"> <input type="text" ng-model="record.title" class="form-control" placeholder="Notes Title" id="cpTitle"> </div> </div> <div class="form-group"> <div class="col-xs-3 text-right"> <label for="cpDesc">Content</label> </div> <div class="col-xs-9"> <textarea class="form-control" ng-model="record.content" rows="3" placeholder="Contents" id="cpDesc"></textarea> </div> </div> </form> </div> </div></div>'),a.put("views/notes.html",'<toaster-container toaster-options="{\'time-out\': 3000, \'close-button\':true}"></toaster-container> <div class="panel panel-primary"> <div class="panel-heading"> <span class="btn-group pull-right"> <button ng-click="createNote()" class="btn btn-success">New</button> </span> <h4>Notes</h4> </div> <div class="panel-body"> <div uib-alert ng-repeat="alert in alerts" ng-class="\'alert-\' + (alert.type || \'warning\')" close="closeAlert($index)">{{alert.msg}}</div> <div ng-repeat="item in data" class="list-group-item clearfix"> <div> <h5 class="mb-1">{{item.title}}</h5> <p class="mb-1">{{item.content}}</p> <span class="btn-group pull-right"> <button class="btn btn-success" ng-click="viewNote(item)">View</button> <button class="btn btn-danger" mwl-confirm title="Delete" message="Confirm Deletion" confirm-text="Delete" cancel-text="Cancel" placement="top" on-confirm="deleteNote(item)" on-cancel="" confirm-button-type="danger" cancel-button-type="default"> Delete </button> </span> </div> </div> </div> </div>'),a.put("views/viewnotes.html",'<toaster-container toaster-options="{\'time-out\': 3000, \'close-button\':true}"></toaster-container> <div class="panel panel-primary"> <div class="panel-heading"> <span class="btn-group pull-right"> <button ng-click="editRecord()" ng-hide="hideEditButton" class="btn btn-success">Edit</button> <button ng-click="saveRecord()" ng-hide="hideSaveButton" class="btn btn-success">Save</button> <button ng-click="backToHome()" class="btn btn-default">Back</button> </span> <h4>View / Edit Notes</h4> </div> <div class="panel-body"> <div class="col-xs-12 col-sm-12"> <form role="form" class="form-horizontal"> <div class="form-group"> <div class="col-xs-3 text-right"> <label for="cpTitle">Title</label> </div> <div class="col-xs-9"> <input type="text" ng-readonly="editEnabled" ng-model="record.title" class="form-control" placeholder="Notes Title" id="cpTitle"> </div> </div> <div class="form-group"> <div class="col-xs-3 text-right"> <label for="cpDesc">Content</label> </div> <div class="col-xs-9"> <textarea class="form-control" ng-readonly="editEnabled" ng-model="record.content" rows="3" placeholder="Contents" id="cpDesc"></textarea> </div> </div> </form> </div> </div></div>')}]);