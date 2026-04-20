(defn lowercase-keys
  "Return a map with all string keys converted to lowercase.

  Non-string keys are left unchanged.

  Examples:
  (lowercase-keys {\"A\" 1, \"B\" 2, :C 3})
  => {\"a\" 1, \"b\" 2, :C 3}

  (lowercase-keys nil)
  => nil"
  [m])