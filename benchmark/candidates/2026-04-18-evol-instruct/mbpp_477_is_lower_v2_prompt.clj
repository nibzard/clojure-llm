(defn deep-lower
  "Recursively convert all string values in a nested map/vector structure to lower case.

  Examples:
  (deep-lower {:a \"HELLO\" :b [\"WORLD\" {:c \"ClJ\"}]})
  => {:a \"hello\", :b [\"world\" {:c \"clj\"}]}

  Non-string values are left unchanged. Nil is returned as nil."
  [x])