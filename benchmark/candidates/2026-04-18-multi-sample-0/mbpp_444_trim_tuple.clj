(defn trim_tuple
  "Write a function to trim each list by k in the given lists."
  [test_list K]
  (let [k (max 0 (or K 0))]
    (mapv (fn [lst]
            (let [xs (or lst '())]
              (drop-last k xs)))
          (or test_list []))))