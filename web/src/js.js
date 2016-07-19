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
  $('.updatable.'+l+'-'+c).attr('data-id', id);
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

var emptyFile = "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,\n"
+"10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10";

setup(emptyFile);

/* Download button
$('#download').on('click', function() {
    var csvContent = "data:text/csv;charset=utf-8,";

    for(var i = 0; i < 23; i++) {
        for(var j = 0; j < 23; j++) {
            csvContent += $($('.updatable')[i * 23 + j]).data('id') + ',';
        }
        csvContent += '\n';
    }

    var encodedUri = encodeURI(csvContent);
    var link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "my_data.csv");
    document.body.appendChild(link); // Required for FF

    link.click();
});*/
