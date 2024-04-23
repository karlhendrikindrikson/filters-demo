module.exports = {
    parserOptions: {
        project: './tsconfig.eslint.json',
    },
    extends: ['plugin:@angular-eslint/recommended'],

    plugins: ['rxjs', 'rxjs-angular', 'unused-imports'],

    overrides: [
        {
            files: ['*.ts'],
            extends: [
                'plugin:@angular-eslint/recommended',
                // This is required if you use inline templates in Components
                'plugin:@angular-eslint/template/process-inline-templates',
                'plugin:rxjs/recommended',
                'plugin:prettier/recommended',
            ],
            rules: {
                'unused-imports/no-unused-imports': 'error',
                'unused-imports/no-unused-vars': [
                    'error',
                    {
                        vars: 'all',
                        varsIgnorePattern: '^_',
                        args: 'after-used',
                        argsIgnorePattern: '^_',
                    },
                ],
            },
        },
        {
            files: ['*.html'],
            extends: ['plugin:@angular-eslint/template/recommended'],
            rules: {
                '@angular-eslint/template/eqeqeq': [2, { allowNullOrUndefined: true }],
            },
        },
    ],
};
