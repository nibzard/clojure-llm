(defn max_run_uppercase
  "Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (let [chars (seq (or test_str ""))
        runs (map count (re-seq #"[A-Z]+" (apply str chars)))]
    (if (seq runs) (apply max runs) 0)))