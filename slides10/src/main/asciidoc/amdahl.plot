set terminal png size 1200,800
set output 'amdahl.png'
set xrange [1:100]
set yrange [1:10]

set grid  xtics ytics
a = 1
f(x) = x / (1 + a * 0.1 * (x-1)) 

# Plot
plot for [a=1:7] f(x) with lines linestyle a  linewidth 3 title sprintf("%g", a / 10.0)
