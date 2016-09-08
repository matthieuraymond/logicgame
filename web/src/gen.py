a = '\t<div class="block" data-id="'
b = '"><img src="blocks/void.png" data-blockid="" /></div>';

for i in range(56):
    if i % 7 == 0:
        print('</div>')
        print('<hr />')
        print('<div class="rule">')
    print(a + str(i) + b);
