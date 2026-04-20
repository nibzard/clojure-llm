(defn next-Perfect-Square
  "Write a cljthon function to find the next perfect square greater than a given number."
  [N]
  (let [root (int (Math/floor (Math/sqrt N)))]
    (let [next-root (inc root)]
      (* next-root next-root))))