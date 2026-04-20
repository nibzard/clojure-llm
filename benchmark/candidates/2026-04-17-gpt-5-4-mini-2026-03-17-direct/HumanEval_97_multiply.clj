(defn multiply
  [a b]
  (* (Math/abs (mod a 10))
     (Math/abs (mod b 10))))