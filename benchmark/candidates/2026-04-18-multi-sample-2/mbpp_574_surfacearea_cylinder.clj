(defn surfacearea_cylinder
  "Write a function to find the surface area of a cylinder."
  [r h]
  (when (and (number? r) (number? h))
    (* 2 Math/PI r (+ r h))))