(defn tetrahedron-volume
  "Return the volume of a regular tetrahedron with edge length `side`.

  Accepts numeric input and returns 0.0 for nil or non-positive sides.

  Examples:
  (tetrahedron-volume 1)
  ;; => 0.11785113019775793

  (tetrahedron-volume 2)
  ;; => 0.9428090415820634

  (tetrahedron-volume nil)
  ;; => 0.0"
  [side])