'use strict';

/**
 * @ngdoc service
 * @name clientApp.notesService
 * @description
 * # notesService
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('notesService', function ($http) {
    
    this.getRecords = function(){
       return $http.get('http://localhost:8090/notes')
    }
    

    this.getRecord = function(id){
      return $http.get('http://localhost:8090/notes/' +id )
    }

    this.createRecord = function(record){
     
      return $http({
        method : 'POST',
        url :'http://localhost:8090/notes/',
        data :record
      });
    }

    this.updateRecord = function(id,record){
      console.log("Updating Record :" + id);
      return $http({
        method : 'PUT',
        url :'http://localhost:8090/notes/ ' +id ,
        data :record
      });
    }


    this.deleleRecord = function(id){
      console.log("Deleting Record :" + id);
      return $http({
        method : 'DELETE',
        url :'http://localhost:8090/notes/'+id
      });
    }

  });
