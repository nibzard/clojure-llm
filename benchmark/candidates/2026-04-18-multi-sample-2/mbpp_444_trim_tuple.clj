(defn trim_tuple
  "	Write a function to trim each list by k in the given lists."
  [test_list K]
  (mapv (fn [lst]
          (let [n (max 0 (or K 0))]
            (if (sequential? lst)
              (vec (drop-last n lst))
              lst)))
        (or test_list [])))