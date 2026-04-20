(defn lateralsurface_cone
  "	Write a function to find the lateral surface area of a cone given radius r and the height h."
  [r h]
  (let [r (double (or r 0))
        h (double (or h 0))]
    (* Math/PI r (Math/sqrt (+ (* r r) (* h h))))))