(defn max_run_uppercase
  "Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (if (nil? test_str)
    0
    (let [runs (->> (re-seq #"[A-Z]+" test_str)
                    (map count))]
      (if (empty? runs)
        0
        (apply max runs)))))