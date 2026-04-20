(defn max-fill
  "You are given a rectangular grid of wells. Each row represents a single well,
  and each 1 in a row represents a single unit of water.
  Each well has a corresponding bucket that can be used to extract water from it, 
  and all buckets have the same capacity.
  Your task is to use the buckets to empty the wells.
  Output the number of times you need to lower the buckets."
  [grid capacity]
  (let [total-water (reduce + (mapcat identity grid))]
    (quot (+ total-water (dec capacity)) capacity)))