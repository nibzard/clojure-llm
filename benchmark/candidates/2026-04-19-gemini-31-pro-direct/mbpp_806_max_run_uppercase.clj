(defn max_run_uppercase
  "Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (if test_str
    (apply max (cons 0 (map count (re-seq #"[A-Z]+" test_str))))
    0))