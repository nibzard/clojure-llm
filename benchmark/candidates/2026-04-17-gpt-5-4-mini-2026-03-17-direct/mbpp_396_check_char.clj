(defn check_char
  "Write a function to check whether the given string starts and ends with the same character or not."
  [string]
  (when (and (string? string) (not (empty? string)))
    (= (first string) (last string))))