(defn uppercase-keywords
  "Return a new map with all keyword keys converted to uppercase strings.

  Non-keyword keys are left unchanged. Nil keys are preserved.

  Examples:
  (uppercase-keywords {:a 1 :foo 2 \"bar\" 3})
  ;; => {\"A\" 1 \"FOO\" 2 \"bar\" 3}

  (uppercase-keywords {nil 1 :x 2})
  ;; => {nil 1 \"X\" 2}"
  [m])