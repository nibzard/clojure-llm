(defn split-camel-keys
  "Return a vector of keywords split from a camelCase or PascalCase string.
  
  Consecutive capital letters should stay together as one token.
  Return nil when input is nil.
  
  Examples:
  (split-camel-keys \"userIDNumber\") ;=> [:user :id :number]
  (split-camel-keys \"HTTPServerError\") ;=> [:http :server :error]
  (split-camel-keys nil) ;=> nil"
  [s])