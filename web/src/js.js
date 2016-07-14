/*
screen.x = map.x * TILE_WIDTH_HALF - map.y * TILE_WIDTH_HALF;
screen.y = map.x * TILE_HEIGHT_HALF + map.y * TILE_HEIGHT_HALF;
*/

function setup(textFile) {
  var $map = $('#map');
  var lines = textFile.split('\n');

  $map.html('');

  for (var line = 0; line < lines.length; line++) { // x
    var ids = lines[line].split(',');
    for (var column = 0; column < ids.length; column++) { // y
      if (ids[column] > 0) {
        $map.append('<div class="tile" data-id="'+ids[column]+'" style="top:'+ (column + line) * 16 +'px;left:'+ (200 + (column - line) * 32) +'px">');
      }
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
