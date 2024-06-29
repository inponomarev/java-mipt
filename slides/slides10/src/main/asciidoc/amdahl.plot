set term epslatex standalone size 18cm, 11cm 
# fontscale 0.3

set output 'usl-amdahl.tex'
set xlabel "{$N$}"
set ylabel "{$S$}"
set xrange [1:100]
set yrange [1:10]

set title "\\scalebox{2.0}{$S = \\frac{N}{1+\\alpha(N-1)} < \\frac{1}{\\alpha}$}"

#set grid  xtics ytics

f(x) = x / (1 + a * 0.1  * (x-1) ) 

set label "\\colorbox{white}{$\\alpha = 0$}"       at  7,  8.0 rotate by   80 front
set label "\\colorbox{white}{$\\alpha = 0.1$}"     at 15,  6.6 rotate by   36 front
set label "\\colorbox{white}{$\\alpha = 0.2$}"     at 20,  4.4 rotate by   10 front
set label "\\colorbox{white}{$\\alpha = 0.3$}"     at 25,  3.3 rotate by   3 front
set label "\\colorbox{white}{$\\alpha = 0.4$}"     at 30,  2.6 rotate by   1 front
set label "\\colorbox{white}{$\\alpha = 0.5$}"     at 35,  2.15 rotate by   0 front


unset key

# Plot
plot for [a=0:7] f(x) with lines linestyle a  linewidth 3 title sprintf("%g", a/10.0)

unset output
!pdflatex usl-amdahl.tex