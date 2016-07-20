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

/* Download button */
$('#download').on('click', function() {
    var csvContent = headTMX;

    for(var i = 0; i < 23; i++) {
        for(var j = 0; j < 23; j++) {
            csvContent += $($('.updatable')[i * 23 + j]).data('id') + ',';
        }
        csvContent += '\n';
    }

    csvContent += tailTMX;

    window.open('data:text/xml;charset=utf-8,' + encodeURI(csvContent));
});

var tailTMX = '</data>\n'
+'                </layer>\n'
+'                <objectgroup name="Borders">\n'
+'                 <object id="6" x="30.6667" y="670.667">\n'
+'                  <polyline points="0,0 644.667,-639.333 1410,128.667 770,771.333 0.666667,2"/>\n'
+'                 </object>\n'
+'                </objectgroup>\n'
+'               </map>';

var headTMX = '<?xml version="1.0" encoding="UTF-8"?>'
+'               <map version="1.0" orientation="isometric" renderorder="right-down" width="23" height="23" tilewidth="128" tileheight="64" nextobjectid="8">'
+'                <tileset firstgid="1" name="floor" tilewidth="128" tileheight="64" tilecount="8" columns="8">'
+'                 <image source="../tiles.png" width="1024" height="64"/>'
+'                 <tile id="0">'
+'                  <properties>'
+'                   <property name="type" value="white"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="1">'
+'                  <properties>'
+'                   <property name="type" value="red"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="2">'
+'                  <properties>'
+'                   <property name="type" value="yellow"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="3">'
+'                  <properties>'
+'                   <property name="type" value="green"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="4">'
+'                  <properties>'
+'                   <property name="type" value="purple"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="5">'
+'                  <properties>'
+'                   <property name="type" value="orange"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="6">'
+'                  <properties>'
+'                   <property name="type" value="gold"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="7">'
+'                  <properties>'
+'                   <property name="type" value="question"/>'
+'                  </properties>'
+'                 </tile>'
+'                </tileset>'
+'                <tileset firstgid="9" name="lava" tilewidth="128" tileheight="64" tilecount="1" columns="1">'
+'                 <image source="../lava.png" width="129" height="65"/>'
+'                 <tile id="0">'
+'                  <properties>'
+'                   <property name="type" value="lava"/>'
+'                  </properties>'
+'                 </tile>'
+'                </tileset>'
+'                <tileset firstgid="10" name="water" tilewidth="128" tileheight="64" tilecount="16" columns="16">'
+'                 <image source="../water_tile_long.png" width="2048" height="64"/>'
+'                 <tile id="0">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="1">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="2">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="3">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="4">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="5">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="6">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="7">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="8">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="9">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="10">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="11">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="12">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="13">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="14">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                 <tile id="15">'
+'                  <properties>'
+'                   <property name="type" value="water"/>'
+'                  </properties>'
+'                 </tile>'
+'                </tileset>'
+'                <layer name="Floor" width="23" height="23">'
+'                 <data encoding="csv">';
