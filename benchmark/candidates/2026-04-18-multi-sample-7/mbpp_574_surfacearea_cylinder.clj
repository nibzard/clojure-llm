(defn surfacearea_cylinder
  "	Write a function to find the surface area of a cylinder."
  [r h]
  (let [r (double (or r 0))
        h (double (or h 0))]
    (* 2 Math/PI r (+ r h))))