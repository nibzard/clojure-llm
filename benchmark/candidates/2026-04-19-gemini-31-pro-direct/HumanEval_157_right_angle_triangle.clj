(defn right_angle_triangle
  "Given the lengths of the three sides of a triangle. Return true if the three
  sides form a right-angled triangle, false otherwise.
  A right-angled triangle is a triangle in which one angle is right angle or 
  90 degree.
  Example:
  >>> (right_angle_triangle 3 4 5)
  true
  >>> (right_angle_triangle 1 2 3)
  false"
  [a b c]
  (let [[x y z] (sort [a b c])]
    (and (pos? x)
         (== (+ (* x x) (* y y)) (* z z)))))