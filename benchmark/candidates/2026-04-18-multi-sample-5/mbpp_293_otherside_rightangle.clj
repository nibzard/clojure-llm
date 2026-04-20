(defn otherside_rightangle
  "	Write a function to find the third side of a right angled triangle."
  [w h]
  (when (and (number? w) (number? h))
    (Math/sqrt (+ (* w w) (* h h)))))