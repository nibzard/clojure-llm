(defn surfacearea_cylinder
  "	Write a function to find the surface area of a cylinder."
  [r h]
  (let [pi (Math/PI)]
    (* pi r (+ (* 2 h) (* 2 r)))))