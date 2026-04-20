(defn lateralsurface_cone
  "Write a function to find the lateral surface area of a cone given radius r and the height h."
  [r h]
  (when (and (number? r) (number? h))
    (* Math/PI r (Math/sqrt (+ (* r r) (* h h))))))