(defn next_Perfect_Square
  "	Write a cljthon function to find the next perfect square greater than a given number."
  [N]
  (let [n (or N 0)
        root (Math/sqrt (double n))
        next-root (inc (long (Math/floor root)))]
    (* next-root next-root)))