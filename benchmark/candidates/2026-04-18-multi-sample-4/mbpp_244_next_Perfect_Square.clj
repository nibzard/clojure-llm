(defn next_Perfect_Square
  " Write a cljthon function to find the next perfect square greater than a given number."
  [N]
  (let [n (long (or N 0))
        root (long (Math/floor (Math/sqrt n)))
        next-root (if (= (* root root) n) (inc root) (inc root))]
    (* next-root next-root)))