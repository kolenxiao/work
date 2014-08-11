/* ---------------------------------------------------------------------- */
/*	GBin1.com Living form
/* ---------------------------------------------------------------------- */
$(function(){
    $(".input-wrapper").livingElements("img/input-mask-white.png", {
        background: "url('img/living-gradient.png') no-repeat",
        easing: 'linear',
        triggerElementSelector: 'input',
        mainAnimationStartOpacity: 0,
        mainAnimationEndOpacity: 1,
        mainAnimationDuration: 800
    });
    
    $(".textarea-wrapper").livingElements("img/textarea-mask.png", {
        background: "url('img/textarea-gradient.jpg') no-repeat",
        easing: 'linear',
        triggerElementSelector: 'textarea',
        preAnimationStartOpacity: 0,
        mainAnimationFade: false,
        scrollDirection: 'horizontal',
        mainAnimationDuration: 1500,
        mainAnimationStartBackgroundPositionX: -200,
        mainAnimationEndBackgroundPositionX: 0,
        postAnimationEndOpacity: 0
    });
});

$(function(){
	$('#tags').tagHandler({
		assignedTags: [ 'GBin1'],
		availableTags: [ 'HTML', 'CSS', 'Javascript', 'Dojo', 'Mootools', 'jQuery', 'jQuery插件', 'SEO', '素材', '图标' ],
		autocomplete: true,
		onAdd: function(tag){
			var addflag = true,tags = $('#tags').tagHandler("getTags");
			jQuery.each(tags, function (i, e) {
				if(tag.toUpperCase()===e.toUpperCase()){
					$('#tags').find('.tagItem').each(function(){
						if($(this).html().toLocaleUpperCase()===tag.toLocaleUpperCase()){
							$(this).animate({opacity: 0.55}).delay(20).animate({opacity: 1}).animate({opacity: 0.55}).delay(20).animate({opacity: 1});
						}
					});
					//$('#log').hide(0).html("标签已存在").show().delay(2000).fadeOut();
					addflag = false;
				}
			});
			return addflag;
		}
	});
});

$(function(){
	$("#commentform").validationEngine('attach');
	$("#submit").click(function(){
		if(!$("#commentform").validationEngine('validate')){
			return;
		}
		var title,body;
		title =  $('#title').val();
		body =  $('#body').val();
		tags = $('#tags').tagHandler("getTags");
		$('#comments').hide().append('<div class="item">' + title +  '&nbsp;'  + new Date() + '</div>').append('<div class="itemtxt">' + body + '</div>').append('<div class="tag">Tags: ' + tags + '</div>').fadeIn(1000);
	});	
	
});