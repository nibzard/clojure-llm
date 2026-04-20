(defn next_Perfect_Square
  "Write a clojure function to find the next perfect square greater than a given number."
  [N]
  (if (< N 0)
    0
    (let [root (long (Math/floor (Math/sqrt N)))
          next-root (inc root)]
      (* next-root next-root))))