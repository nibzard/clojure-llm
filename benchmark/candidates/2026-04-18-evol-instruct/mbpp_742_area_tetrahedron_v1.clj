(defn weighted-tetrahedron-surface-area
  "Return the surface area of a tetrahedron when each of its 4 triangular faces
  can have a different edge length.

  The input is a map with keys :a, :b, :c, and :d, representing the side
  length of each face. Use Heron's formula for each face and sum the areas.

  Examples:
  (weighted-tetrahedron-surface-area {:a 1 :b 1 :c 1 :d 1})
  ;;=> 1.7320508075688772

  (weighted-tetrahedron-surface-area {:a 2 :b 2 :c 2 :d 2})
  ;;=> 6.928203230275509"
  [faces]
  (letfn [(triangle-area [s]
            (let [s (/ (+ s s s) 2.0)]
              (Math/sqrt (* s (- s s) (- s s) (- s s)))))]
    (+ (triangle-area (:a faces))
       (triangle-area (:b faces))
       (triangle-area (:c faces))
       (triangle-area (:d faces)))))