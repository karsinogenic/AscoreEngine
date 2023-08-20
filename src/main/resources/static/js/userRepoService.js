(function(){
    
    var userRepoService = function($http){
      
      var getUsers = function(username){
            return $http.get("http://localhost:8080/api/all")
                        .then(function(response){
                            console.log(response)
                           return response.data; 
                        });
      };
  
      return {
          get: getUsers
      };
        
    };
    
    var module = angular.module("postExample");
    module.factory("userRepoService", userRepoService);
    
}());