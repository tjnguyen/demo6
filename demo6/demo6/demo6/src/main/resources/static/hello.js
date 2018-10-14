var myApp = angular.module('demo', []);

		myApp.controller('hello', ['$scope', '$http', function($scope, $http) {
			
			$scope.customerName = "";
			$scope.greeting = "";
			var resp =  {"data":{"message":""},"status":200,"config":{}};
			
		    $scope.callingGreeting = function(customerName) {
		    	console.log('testing for ' + customerName);
		        $http.get("http://localhost:8222/demo/Hello/"+ customerName).
		            then(function(response) {
		            	resp = response;
		            	console.log('get response ' + resp.data);
		                $scope.greeting = resp.data.message;
		            });
		        
		    };
		    
		}]);