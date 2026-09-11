set terminal svg size 1800,900 enhanced  font "Arial,20"

set datafile separator comma
set datafile missing ""

set xdata time
set timefmt "%Y-%m-%d"
set format x "%Y"

# Shared horizontal scale
set xrange ["1995-01-01":"2017-12-31"]

# Shared horizontal geometry for perfect alignment
set lmargin at screen 0.08
set rmargin at screen 0.98
set tmargin at screen 0.96
set bmargin at screen 0.54

# Okabe-Ito palette
c_color          = "#000000"
csharp_color     = "#0072B2"
cpp_color        = "#56B4E9"
java_tiobe_color = "#D55E00"
js_color         = "#009E73"
python_color     = "#CC79A7"

# Timeline colours
java_release_color = "#D55E00"   # same as Java curve
event_color        = "#E69F00"   # separate orange

set multiplot

# ============================================================
# TOP: TIOBE index
# ============================================================

set tmargin at screen 0.95
set bmargin at screen 0.42

set yrange [0:*]

set ylabel "TIOBE index (%)"
unset xlabel

set format x "%Y"
set xtics \
("1995" "1995-01-01", \
"1997" "1997-01-01", \
"1999" "1999-01-01", \
"2001" "2001-01-01", \
"2003" "2003-01-01", \
"2005" "2005-01-01", \
"2007" "2007-01-01", \
"2009" "2009-01-01", \
"2011" "2011-01-01", \
"2013" "2013-01-01", \
"2015" "2015-01-01", \
"2017" "2017-01-01", \
"2019" "2019-01-01")

set xtics font ",18"
set ytics font ",18"

set grid xtics ytics linewidth 1.5 linecolor black
set key top right font ",18"

set label 1 "Data: TIOBE Index https://www.tiobe.com/tiobe-index/" \
at graph 0.02,0.96 font ",18" front boxed

set style line 1 lc rgb c_color          lw 3
set style line 2 lc rgb csharp_color     lw 3
set style line 3 lc rgb cpp_color        lw 3
set style line 4 lc rgb java_tiobe_color lw 3
set style line 5 lc rgb js_color         lw 3
set style line 6 lc rgb python_color     lw 3

plot "tiobe-top6-wide.csv" using 1:2 \
with lines ls 1 title "C", \
"" using 1:3 \
with lines ls 2 title "C#", \
"" using 1:4 \
with lines ls 3 title "C++", \
"" using 1:5 \
with lines ls 4 title "Java", \
"" using 1:6 \
with lines ls 5 title "JavaScript", \
"" using 1:7 \
with lines ls 6 title "Python"


# ============================================================
# BOTTOM: Java / JVM timeline
# ============================================================

set datafile separator whitespace

set tmargin at screen 0.38
set bmargin at screen 0.06

# Deliberately retain generous vertical coordinate space.
# Labels must not be squeezed against the upper boundary.
set yrange [-1.75:1.85]

unset key
unset border
unset label 1

set xtics \
    ("" "1995-01-01", \
     "" "1997-01-01", \
     "" "1999-01-01", \
     "" "2001-01-01", \
     "" "2003-01-01", \
     "" "2005-01-01", \
     "" "2007-01-01", \
     "" "2009-01-01", \
     "" "2011-01-01", \
     "" "2013-01-01", \
     "" "2015-01-01", \
     "" "2017-01-01", \
     "" "2019-01-01")

set grid xtics
set tics nomirror out
set zeroaxis linewidth 2

unset ytics
unset ylabel

set style line 11 lc rgb java_release_color lw 3 pt 7 ps 1.0
set style line 12 lc rgb event_color        lw 3 pt 5 ps 1.0

$java << EOD
1996-01-23   -0.72   -1.18   "JDK 1.0"       -0.95   "23 Jan 1996"
1997-02-19   -1.15   -1.66   "JDK 1.1"       -1.43   "19 Feb 1997"
1998-12-08   -0.72   -1.18   "J2SE 1.2"      -0.95   "8 Dec 1998"
2000-05-08   -1.15   -1.66   "J2SE 1.3"      -1.43   "8 May 2000"
2002-02-06   -0.72   -1.18   "J2SE 1.4"      -0.95   "6 Feb 2002"
2004-09-30   -1.15   -1.66   "J2SE 5.0"      -1.43   "30 Sep 2004"
2006-12-11   -0.72   -1.18   "Java SE 6"     -0.95   "11 Dec 2006"
2011-07-28   -1.15   -1.66   "Java SE 7"     -1.43   "28 Jul 2011"
2014-03-18   -0.72   -1.18   "Java SE 8"     -0.95   "18 Mar 2014"
2017-09-21   -1.15   -1.66   "Java SE 9"     -1.43   "21 Sep 2017"
2018-03-20   -0.20    -0.75   "Java SE 10"    -0.52    "20 Mar 2018"
EOD

$extra << EOD
2004-01-01    0.40    0.84   "Scala"              0.62   "early 2004"
2007-01-02    0.80    1.26   "Groovy 1.0"         1.03   "2 Jan 2007"
2010-01-27    0.55    1.01   "Oracle buys Sun"    0.78   "27 Jan 2010"
2016-02-15    0.80    1.26   "Kotlin 1.0"         1.03   "15 Feb 2016"
EOD

set label 1 "Data: Wikipedia https://en.wikipedia.org/wiki/Java\\\_version\\\_history" \
at graph 0.02,0.80 font ",18" front boxed

plot \
    $java using 1:2 with impulses ls 11 notitle, \
    $java using 1:2 with points   ls 11 notitle, \
    $java using 1:3:4 with labels boxed center tc rgb "black" font ",20" notitle, \
    $java using 1:5:6 with labels boxed center tc rgb "black" font ",16" notitle, \
    $extra using 1:2 with impulses ls 12 notitle, \
    $extra using 1:2 with points   ls 12 notitle, \
    $extra using 1:3:4 with labels boxed center tc rgb "black" font ",20" notitle, \
    $extra using 1:5:6 with labels boxed center tc rgb "black" font ",16" notitle

unset multiplot
