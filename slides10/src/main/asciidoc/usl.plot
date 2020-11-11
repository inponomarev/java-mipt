set terminal png size 1200,800
set output 'usl.png'
set xrange [1:100]
set yrange [1:10]
set title "alpha = 0.1"
set grid  xtics ytics
a = 1
f(x) = x / (1 + (a * 0.1 + b * 0.001 * x ) * (x-1) ) 

# Plot
plot for [b=0:5] f(x) with lines linestyle b linewidth 3 title sprintf("%g", b/1000.0)
