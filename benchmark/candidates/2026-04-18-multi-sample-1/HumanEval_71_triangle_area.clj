(defn triangle_area
  "Given the lengths of the three sides of a triangle. Return the area of
  the triangle rounded to 2 decimal points if the three sides form a valid triangle. 
  Otherwise return -1
  Three sides make a valid triangle when the sum of any two sides is greater 
  than the third side.
  Example:
  >>> (triangle_area 3 4 5)
  6.0
  >>> (triangle_area 1 2 10)
  -1"
  [a b c]
  (if (and (number? a)
           (number? b)
           (number? c)
           (pos? a)
           (pos? b)
           (pos? c)
           (> (+ a b) c)
           (> (+ a c) b)
           (> (+ b c) a))
    (let [s (/ (+ a b c) 2.0)
          area (Math/sqrt (* s (- s a) (- s b) (- s c)))]
      (/ (Math/round (* area 100.0)) 100.0))
    -1))