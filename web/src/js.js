/*
screen.x = map.x * TILE_WIDTH_HALF - map.y * TILE_WIDTH_HALF;
screen.y = map.x * TILE_HEIGHT_HALF + map.y * TILE_HEIGHT_HALF;
*/

var step = 1;

function setup(textFile) {
  var $map = $('#map');
  var lines = textFile.split('\n');

  $map.html('');

  for (var line = 0; line < lines.length; line++) { // x
    var ids = lines[line].split(',');
    for (var column = 0; column < ids.length; column++) { // y
      if (ids[column] > 0) {
        var visible = (line+column < 11 || line+column > 33 || column-line > 10 || line-column > 10 ) ? 'not-visible': '';
        $map.append('<div class="tile updatable '+line+'-'+column+' '+ visible +'" data-line="'+line+'" data-column="'+column+'" data-id="'+ids[column]+'" style="top:'+ (column + line) * 16 +'px;left:'+ (200 + (column - line) * 32) +'px">');
      }
    }
  }

  $('.updatable').on('click', function(e) {
    var id = $('#selected').attr('data-id');
    var $this = $(this);
    var line = $this.attr('data-line');
    var column = $this.attr('data-column');
    var left = e.pageX - $this.offset().left;
    var top = e.pageY - $this.offset().top;
    var w = 64;
    var h = 32;

    var deltaC = Math.round((left/w) + (top/h) - 1);
    var deltaL = -1 * Math.round((left/w) - (top/h));

    update(line * 1 + deltaL * 1, column * 1 + deltaC * 1,id);
  });
}

function update(l, c, id) {
  var tile = '.updatable.'+l+'-'+c;
  if (step == 1) {
    $(tile).attr('data-id', id);
  } else if (step == 2) {
    if ($('.selected').data('obj') == "bob-tool") {
      $('#bob').remove();
      $(tile).html('<div class="in-tile-container"><img src="bob.png" class="in-tile" id="bob"/></div>');
    } else if ($('.selected').data('obj') == "light-plus") {
      $(tile).html('<div class="in-tile-container"><img src="light_bulb.png" class="in-tile" /></div>');
    } else if ($('.selected').data('obj') == "light-del") {
      $(tile).html('');
    }
  }
}

// TO REFACTOR
document.getElementById('file').onchange = function(){
  var file = this.files[0];

  var reader = new FileReader();
  reader.onload = function(progressEvent){
    setup(this.result);
  };
  reader.readAsText(file);
};

$('.tile-selector.tile').on('click', function() {
  var id = $(this).data('id');
  $('#selected').attr("data-id", id);
});

// Navigation
$('#tiles-next').on('click', function() {
  $('#tiles-pane').hide();
  $('#objects-pane').show();
  step++;
});

$('#objects-back').on('click', function() {
  $('#tiles-pane').show();
  $('#objects-pane').hide();
  step--;
});

// Object selector
$('.object-selector').on('click', function() {
  $('.object-selector.selected').removeClass('selected');
  $(this).addClass('selected');
});

$('#modeTabs a').click(function(e){
  e.preventDefault();
  $(this).tab('show');
});

$('.input-picker .block').on('click',function(){
  var $this = $(this);
  if ($this.hasClass('picked')) {
    $this.removeClass('picked');
  } else {
    $this.addClass('picked');
  }
});

$('.rule .block').on('click',  function(){
  var id = $(this).data('id');
  $('.input-rule-picker .block').data('id', id);
  $('#blockModal').modal();
});

$('.input-rule-picker .block').on('click', function () {
  var $this = $(this);
  var id = $this.data('id');

  $('.rule .block[data-id='+$this.data('id')+']').html($this.html());
  $('.rule .block[data-id='+$this.data('id')+']').data('string', $this.data('string'));

  $('#blockModal').modal('hide');
});

var emptyFile = "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,\n"
+"9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9";

setup(emptyFile);

/* Download button */
$('#download').on('click', function() {
    var mode = $('.nav-pills .active a').html();
    var map = '<floor>';
    var objects = '<object>';
    var startX = 0;
    var startY = 0;

    for(var i = 0; i < 23; i++) {
        for(var j = 0; j < 23; j++) {
            var $tile = $($('.updatable')[i * 23 + j]);
            map += $tile.data('id') + ',';

            if ($tile.find('img').attr('src') == 'bob.png') {
              startX = j;
              startY = 23 - i - 1;
            }

            if ($tile.find('img').attr('src') == 'light_bulb.png') {
              objects += '25,'
            } else {
              objects += '0,'
            }
        }
        map += '\n';
        objects += '\n';
    }

    map += '</floor>';
    objects += '</object>';

    var file = '<?xml version="1.0" encoding="UTF-8"?>\n';

    file += '<root type="' + mode + '">\n';

    file += '<bob x="' + startX + '" y="' + startY + '" />\n';
    file += '<text>'+$('#help-text').val()+'</text>\n';

    file += '<hints>\n';

    for (var i = 1; i < 4; i++) {
      if ($('#hint'+i).val() != "") {
        file += '\t<hint>' + $('#hint'+i).val() + '</hint>\n';
      }
    }

    file += '</hints>\n';

    if (mode == "WRITE") {
      file += '<inputs>\n';

      var arr = $('.picked');

      for (var i = 0; i < arr.length; i++) {
        file += '\t<block name="' + $(arr[i]).data('string') + '" />\n';
      }

      file += '</inputs>\n';
    }

    var rules = '<rules available="'+8+'">'

    if (mode == "READ") {

      for (var i = 0; i < 56 ; i++) {
        var index = i % 7;
        if (index == 0) {
          rules += '<rule>\n\t';
        }

        var blockString = $('#rules-container .block[data-id="'+i+'"]').data('string');
        rules += '<block name="'+ blockString +'" />';

        if (index == 6) {
          rules += '\n</rule>\n';
        }

      }
    }

    rules += '</rules>';

    file += rules
    file += map;
    file += objects;
    file += "</root>";

    download(file);
});

function download(content) {
    var blob = new Blob([content], {type : 'text/xml'});

    var downLink = document.createElement("A");
    downLink.innerHTML = "File ready!";
    downLink.download = "custom.xml";
    downLink.href = window.URL.createObjectURL(blob);
    downLink.click();
}


var headTMX = '';
