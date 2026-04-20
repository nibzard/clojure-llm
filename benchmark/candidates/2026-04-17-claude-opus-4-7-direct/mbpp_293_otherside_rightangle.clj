(defn otherside_rightangle
  "Write a function to find the third side of a right angled triangle."
  [w h]
  (if (nil? w)
    h
    (if (nil? h)
      w
      (Math/sqrt (+ (* w w) (* h h))))))