(defn filter_by_prefix
  " Filter an input list of strings only for ones that start with a given prefix.
  >>> (filter_by_prefix [] \"a\")
  []
  >>> (filter_by_prefix [\"abc\" \"bcd\" \"cde\" \"array\"] \"a\")
  [\"abc\" \"array\"]"
  [strings prefix]
  (vec (filter #(and (string? %) (string? prefix) (.startsWith ^String % prefix))
               (or strings []))))