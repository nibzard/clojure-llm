(defn surfacearea_cylinder
  "	Write a function to find the surface area of a cylinder."
  [r h]
  (let [pi Math/PI]
    (+ (* 2 pi r r)
       (* 2 pi r h))))