(defn surfacearea_cylinder
  "	Write a function to find the surface area of a cylinder."
  [r h]
  (let [r (double r)
        h (double h)]
    (if (and (number? r) (number? h) (pos? r) (pos? h))
      (+ (* 2 Math/PI r (+ r h))
         (* 2 Math/PI r h))
      0.0)))