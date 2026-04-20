(defn next_Perfect_Square
  "Write a cljthon function to find the next perfect square greater than a given number."
  [N]
  (let [n (long N)
        r (long (Math/floor (Math/sqrt n)))]
    (if (= (* r r) n)
      (* (inc r) (inc r))
      (* (inc r) (inc r)))))