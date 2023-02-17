AOS.init({
	duration: 800,
	easing: 'slide',
	once: true
});

jQuery(document).ready(function($) {
	
	getUserMe();
	"use strict";

	getOwnerMe();
	"use strict";

	var preloader = function() {

		var loader = document.querySelector('.loader');
		var overlay = document.getElementById('overlayer');

		function fadeOut(el) {
			el.style.opacity = 1;
			(function fade() {
				if ((el.style.opacity -= .1) < 0) {
					el.style.display = "none";
				} else {
					requestAnimationFrame(fade);
				}
			})();
		};
	
		setTimeout(function() {
			fadeOut(loader);
			fadeOut(overlay);
		}, 200);
	};
	preloader();
	
	
	var tinySdlier = function() {

		var heroSlider = document.querySelectorAll('.hero-slide');
		var propertySlider = document.querySelectorAll('.property-slider');
		var imgPropertySlider = document.querySelectorAll('.img-property-slide');
		var testimonialSlider = document.querySelectorAll('.testimonial-slider');
		

		if ( heroSlider.length > 0 ) {
			var tnsHeroSlider = tns({
				container: '.hero-slide',
				mode: 'carousel',
				speed: 700,
				autoplay: true,
				controls: false,
				nav: false,
				autoplayButtonOutput: false,
				controlsContainer: '#hero-nav',
			});
		}


		if ( imgPropertySlider.length > 0 ) {
			var tnsPropertyImageSlider = tns({
				container: '.img-property-slide',
				mode: 'carousel',
				speed: 700,
				items: 1,
				gutter: 30,
				autoplay: true,
				controls: false,
				nav: true,
				autoplayButtonOutput: false
			});
		}

		if ( propertySlider.length> 0 ) {
			var tnsSlider = tns({
				container: '.property-slider',
				mode: 'carousel',
				speed: 700,
				gutter: 30,
				items: 3,
				autoplay: true,
				autoplayButtonOutput: false,
				controlsContainer: '#property-nav',
				responsive: {
					0: {
						items: 1
					},
					700: {
						items: 2
					},
					900: {
						items: 3
					}
				}
			});
		}


		if ( testimonialSlider.length> 0 ) {
			var tnsSlider = tns({
				container: '.testimonial-slider',
				mode: 'carousel',
				speed: 700,
				items: 3,
				gutter: 50,
				autoplay: true,
				autoplayButtonOutput: false,
				controlsContainer: '#testimonial-nav',
				responsive: {
					0: {
						items: 1
					},
					700: {
						items: 2
					},
					900: {
						items: 3
					}
				}
			});
		}
	}
	tinySdlier();

});

function getUserMe() {
	var settings = {
		"url": "http://localhost:8080/api/profile",
		"method": "GET",
		"timeout": 0,
		"headers": {
		  "Authorization": localStorage.getItem('accessToken')
		},
	  };
	  
	  $.ajax(settings).done(function (response, status, xhr) {
		console.log(response);
		console.log(status)
		if (status === 403) {
			window.location = "/login.html"
		}
		console.log(response.nickName);
		$('#loginUser').empty();
		$('#loginUser').append(response.nickName + "님 환영합니다.");
		$('#mypage').show();
		$('#MainLogout').show();
		$('#MainLogin').hide();
		$('#MainSignUp').hide();
	  });
}

function getOwnerMe() {
	var settings = {
		"url": "http://localhost:8080/api/profile/owner",
		"method": "GET",
		"timeout": 0,
		"headers": {
		  "Authorization": localStorage.getItem('accessToken')
		},
	  };
	  
	  $.ajax(settings).done(function (response, status, xhr) {
		console.log(response);
		console.log(status)
		if (status === 403) {
			window.location = "/login.html"
		}
		console.log(response.nickName);
		$('#loginUser').show();
		$('#loginUser').append(response.nickName + "님 환영합니다.");
		$('#mypage').show();
		$('#MainLogout').show();
		$('#MainLogin').hide();
		$('#MainSignUp').hide();
	  });
}


//로그아웃
function logout() {
	var settings = {
		"url": "http://localhost:8080/api/user/logout",
		"method": "DELETE",
		"timeout": 0,
		"headers": {
		  "Authorization": localStorage.clear('accessToken')
		},
	  };
	  
	  $.ajax(settings).done(function (response, status, xhr) {
		console.log(response);
		console.log(status)
		if (status === 403) {
			window.location = "/index.html"
		}
		console.log(response.nickName);
		$('#loginUser').hide();
		$('#mypage').hide();
		$('#MainLogout').hide();
		$('#MainLogin').show();
		$('#MainSignUp').show();
		$('#loginUser').clear(response.nickName + "님 환영합니다.");
	  });
}