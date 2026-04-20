(defn all_prefixes
  " Return list of all prefixes from shortest to longest of the input string
  >>> (all_prefixes \"abc\")
  [\"a\" \"ab\" \"abc\"]"
  [string]
  (mapv #(subs string 0 %) (range 1 (inc (count string)))))