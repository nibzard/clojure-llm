(defn trim_tuple
  "	Write a function to trim each list by k in the given lists."
  [test_list K]
  (let [k (max 0 (or K 0))]
    (if (nil? test_list)
      nil
      (mapv (fn [lst]
              (let [v (vec (or lst []))
                    n (count v)]
                (subvec v 0 (max 0 (- n k)))))
            test_list))))