{ pkgs }: {
  deps = [
    pkgs.python39Packages.pip
    pkgs.qtile
    pkgs.zulu
    pkgs.nodePackages.vscode-langservers-extracted
    pkgs.nodePackages.typescript-language-server  
  ];
}