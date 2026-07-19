set term epslatex standalone size 18cm, 11cm 
# fontscale 0.3

set output 'usl.tex'
set xlabel "{$N$}"
set ylabel "{$S$}"
set xrange [1:100]
set yrange [1:10]

set title "\\scalebox{2.0}{$S = \\frac{N}{1+\\alpha(N-1) + \\beta N (N-1)} = \\frac{N}{1+(\\alpha + \\beta N) (N-1)} \\rightarrow 0$}"

#set grid  xtics ytics

a = 1
f(x) = x / (1 + (a * 0.1 + b * 0.001 * x ) * (x-1) ) 

set label "\\colorbox{white}{$\\alpha = 0.1$}"  at 10, 8.50 rotate by   0 front
set label "\\colorbox{white}{$\\beta = 0$}"     at 70, 9.10 rotate by   5 front
set label "\\colorbox{white}{$\\beta = 0.001$}" at 70, 5.80 rotate by -10 front
set label "\\colorbox{white}{$\\beta = 0.002$}" at 70, 4.25 rotate by -9 front
set label "\\colorbox{white}{$\\beta = 0.003$}" at 70, 3.50 rotate by -9 front
set label "\\colorbox{white}{$\\beta = 0.004$}" at 70, 2.81 rotate by -8 front
set label "\\colorbox{white}{$\\beta = 0.005$}" at 70, 1.80 rotate by -5 front


unset key

# Plot
plot for [b=0:5] f(x) with lines linestyle b linewidth 3 

# title sprintf("%g", b/1000.0)

unset output
!pdflatex usl.tex